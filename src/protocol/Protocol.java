package protocol;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Protocol {
	public String format(String user, String msg){
		StringBuilder sb = new StringBuilder();
		sb.append(user);
		sb.append("\n");
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString());
		sb.append("\n");
		sb.append(msg);
		sb.append("\n");
		return sb.toString();
	}
	
	public String translate(String msg){
		String[] msgs = msg.split("\n");
		if(msgs.length<3)
			return msg;
		else{
			StringBuilder sb = new StringBuilder();
			sb.append(msgs[1]);
			sb.append("\n");
			sb.append(msgs[0]+": ");
			for(int i=2;i<msgs.length;i++){
				sb.append(msgs[i]);
				sb.append("\n");
			}
			return sb.toString();
		}
	}
}
