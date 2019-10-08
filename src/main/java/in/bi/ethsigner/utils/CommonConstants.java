/*******************************************************************************
 * Copyright (C) 2019 blockintercept
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package in.bi.ethsigner.utils;

/**
 * Quartz Scheduler
 * 
 * @author blockintercept
 * @see www.blockintercept.com
 *
 */

public interface CommonConstants {

	String property_blockintercept_scheduler_cron = "blockintercept.scheduler.cron";
	String JOB_NAME = "Qrtz_Job_Detail";
	String QRTZ_TRIGGER_KEY = "Qrtz_Trigger";
	String TRIGGER_DESC = "Qrtz_Trigger_Desc";
	String H2_PROFILE = "h2";
	String MYSQL_PROFILE = "mysql";
	String property_quartz_cron_expression = "blockintercept.quartz.scheduler.cron";
	String property_quartz_frequency = "blockintercept.quartz.scheduler.frequency.seconds";
	String SPRING_RESOURCE_LOCATION = "spring.config.location";
	String BLOCKINTERCEPT_AGENT_TYPE = "blockintercept.agent.type";
	String AGENT_TYPE_HTTP = "http";
	String BLOCKINTERCEPT_AGENT_URL = "blockintercept.agent.url";
	String ERROR = "error";
	String TRANSACTION_ERROR = "txError";
	String RPC_NODE_ERROR = "rpcError";
	
}
