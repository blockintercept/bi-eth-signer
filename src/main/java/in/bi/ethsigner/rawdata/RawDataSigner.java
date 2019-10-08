/*******************************************************************************
 * * Copyright (C) 2019 blockintercept
 *  * 
 *  * This program is free software: you can redistribute it and/or modify
 *  * it under the terms of the GNU Lesser General Public License as published by
 *  * the Free Software Foundation, either version 3 of the License, or
 *  * (at your option) any later version.
 *  * 
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU Lesser General Public License for more details.
 *  * 
 *  * You should have received a copy of the GNU Lesser General Public License
 *  * along with this program.  If not, see <http://www.gnu.org/licenses/>
 ******************************************************************************/
package in.bi.ethsigner.rawdata;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import in.bi.ethsigner.utils.CommonConstants;
import in.bi.ethsigner.utils.CommonUtils;

/**
 * Raw data transaction for ethereum blockchain.
 * 
 * @author blockintercept
 *
 */

public class RawDataSigner {

	private Web3j web3 = null;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public boolean initConnections(String rpcNode) {
		if (web3 == null) {
			try {
				web3 = Web3j.build(new HttpService(rpcNode));
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return false;
			}
		}
		return true;
	}

	public String addRawData(byte[] data, String privateKey, String rpcNode, String toAddress)
			throws IOException, InterruptedException, ExecutionException {
		logger.info("addRawData ...");
		if (initConnections(rpcNode)) {
			Credentials userCred = Credentials.create(privateKey);
			EthGasPrice price = web3.ethGasPrice().send();
			EthBlock block = web3.ethGetBlockByNumber(DefaultBlockParameterName.LATEST, false).send();
			EthGetTransactionCount ethGetTransactionCount = web3
					.ethGetTransactionCount(userCred.getAddress(), DefaultBlockParameterName.PENDING).send();
			BigInteger nonce = ethGetTransactionCount.getTransactionCount();

			RawTransaction rawTransaction = RawTransaction.createTransaction(nonce, price.getGasPrice(),
					block.getBlock().getGasLimit(), toAddress, BigInteger.ZERO, Numeric.toHexString(data));
			byte[] signedMessage = TransactionEncoder.signMessage(rawTransaction, userCred);
			String hexValue = Numeric.toHexString(signedMessage);
			EthSendTransaction ethSendTransaction = web3.ethSendRawTransaction(hexValue).sendAsync().get();
			String transactionHash = ethSendTransaction.getTransactionHash();
			return transactionHash;

		} else {
			return CommonConstants.RPC_NODE_ERROR;
		}
	}

	public String getRawData(String txHash, String rpcNode) throws IOException {
		logger.info("getRawData ...");
		if (initConnections(rpcNode)) {
			Optional<Transaction> tx = web3.ethGetTransactionByHash(txHash).send().getTransaction();

			while (tx.isPresent()) {
				return CommonUtils.convertHexToString(Numeric.cleanHexPrefix(tx.get().getInput()));
			}
			return CommonConstants.TRANSACTION_ERROR;
		} else {
			return CommonConstants.RPC_NODE_ERROR;
		}
	}	

}
