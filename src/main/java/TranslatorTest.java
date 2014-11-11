
import java.io.IOException;

import enums.JobType;
import service.ServiceDescription;
import utils.APCCommandTranslator;
import utils.JSONUtils;


public class TranslatorTest {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		//System.out.println(readFileToString("sample.json"));
		String servDesc = JSONUtils.readFileToString("sample.json");
		ServiceDescription sd = JSONUtils.fromJSON(servDesc);
		System.out.println(APCCommandTranslator.translateCreationCommand(sd, JobType.DOCKER));
	}
	

}
