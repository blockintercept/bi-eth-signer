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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class RawDataSignerTest {
	
	String data = "HELLO FROM BLOCKINTERCEPT";
	String privateKey = "bda2eaa4e921aab5bb11074ea564a87752618e7cac82a8b33da995cffe2f0aee";
	String rpcNode = "http://localhost:7545";
	String toAddress = "0xEb684D1FAf48418aD15fc89E30394eE02819b15A";
	
	@Test
	public void testDataIO() {
		RawDataSigner signer = new RawDataSigner();
		try {
			String tx = signer.addRawData(data.getBytes(), privateKey, rpcNode, toAddress);
			assertNotNull(tx);
			String out = signer.getRawData(tx, rpcNode);
			System.err.println(out);
			assertEquals(out, data);
			
			
		} catch (IOException | InterruptedException | ExecutionException e) {
			fail(e.getMessage());
		}
	}
	


}
