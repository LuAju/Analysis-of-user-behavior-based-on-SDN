import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DynamicSetUserBandwidth{	
	public static void main(String[] args) throws IOException {		
		//用于记录上次部署策略的类型:Release、General、Strict
		String flag = null; 	
		String sep = File.separator;
		String logFilePath = sep+"home"+sep+"zero"+sep+"ExperimentTest"+sep+"PolicyDeployLog.txt";
		//用于保存策略部署时间及相关信息日志文件
		File logFile = new File(logFilePath); 
		String strURL = "http://localhost:8008/app/dashboard-example/scrip"
						+ "ts/metrics.js/metric/json";
		URL url = new URL(strURL);
		@SuppressWarnings("resource")
		Writer out = new FileWriter(logFile,true);
		BufferedReader buf = null;
		double TotalFlows = 0;
		while(true){
			String temp = null;				
			buf = new BufferedReader(new InputStreamReader(url.openStream()));		
			while ((temp = buf.readLine()) != null) {
				if(temp.trim().startsWith("\"bps_out\":" )){					
					int startIndex = temp.indexOf(":")+1;
					int endIndex = temp.indexOf(",");
					temp =  temp.substring(startIndex,endIndex);
					if(temp.contains(".")){
						int Index = temp.indexOf(".");
						TotalFlows = Double.parseDouble(temp.substring(0, Index).trim())/1000000; //单位M						
					}else{
						TotalFlows = Double.parseDouble(temp.trim())/1000000; //单位M
					}
					
					
					//如果上次部署的不是该类型策略，则部署该类型的策略，并把flag赋值为Release
					if(TotalFlows <= 12){
						if(!"Release".equals(flag)){ 
							String command = "./ReleaseRateLimit.sh";
							Runtime.getRuntime().exec(command);
							flag = "Release";
							String DateTime = new SimpleDateFormat("yyyyMMddHHmmss")
											  .format(new Date());
							System.out.println(DateTime+":执行了ReleaseRateLimit");
							out.write(DateTime+":执行了ReleaseRateLimit"+"\r\n");
							out.flush();
						}											
					}else if(TotalFlows >= 15 && TotalFlows <= 25){
						//如果上次部署的不是该类型策略，则部署该类型的策略，并把flag赋值为General
						if(!"General".equals(flag)){ 
							String command = "./GeneralRateLimit.sh";
							Runtime.getRuntime().exec(command);
							flag = "General";
							String DateTime = new SimpleDateFormat("yyyyMMddHHmmss")
											  .format(new Date());
							System.out.println(DateTime+":执行了GeneralRateLimit");
							out.write(DateTime+":执行了GeneralRateLimit"+"\r\n");
							out.flush();
						}									
					}else if(TotalFlows >= 30){
						//如果上次部署的不是该类型策略，则部署该类型的策略，并把flag赋值为General
						if(!"Strict".equals(flag)){ 
							String command = "./StrictRateLimit.sh";
							Runtime.getRuntime().exec(command);
							flag = "Strict";
							String DateTime = new SimpleDateFormat("yyyyMMddHHmmss")
											  .format(new Date());
							System.out.println(DateTime+":执行了StrictRateLimit");
							out.write(DateTime+":执行了StrictRateLimit"+"\r\n");
							out.flush();
						}
					}										
				}
			}															
		}
	}
}
