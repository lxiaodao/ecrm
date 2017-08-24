/**
 * 
 */
package com.weheros.framework.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.ExecutionContext;

/**
 * @author liuy
 * 
 */
public class HttpClientTest {

	public static void main(String[] arr) throws Exception {

		//executeRecommendPush();
		
		getLastUrl();

	}
   public static void executeRecommendPush() throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
        
        PostMethod method=new PostMethod("http://www.htgdai.com:8096/api/Recmds/RecommendPush");
        //"Content-Type", "text/json; charset=UTF-8"
        method.setRequestHeader("Content-Type",
				"text/json; charset=UTF-8");
    	method.setRequestHeader("Accept",
				"text/json,text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
		String listjson="[{\"IN_Title\":\"习近平谈务实作风\",\"IN_CreateTime\":\"6/21/2017, 9:37:14 PM\",\"IN_Source\":\"澎湃新闻\",\"IN_CommentNum\":\"0\",\"IN_ImgType\":1,\"ImgList\":[{\"ITR_Url\":\"https://imgsa.baidu.com/news/q%3D100/sign=b8364b84f1edab64727249c0c737af81/7af40ad162d9f2d34b1511a0a3ec8a136327cc7c.jpg\"}],\"IN_Type\":0,\"IN_Content\":\"<div><b>【学习小组按】</b>求真务实是党的思想路线的核心内容。对于务实作风，把求真务实的导向立起挥考核指挥棒作用，把求真务实的导向立起来，把真抓实干的规矩严起来，让真干假干不一样、干多干少不一样、干好干坏不一样，确保脱贫攻坚工作成效经得起实践和历史检验。——2017年3月31日，在中共中央政治局会议上的讲话<b>★让埋头苦干、真抓实干的干部真正得到重用</b>要坚持求真务实，察真情、说实话真功、求实效，让埋头苦干、真抓实干的干部真正得到重用、充分施展才华，让作风飘浮、哗众取宠的干部无以表功、受到贬责。——2014年5月9日，在指导兰考县委常委班子党的群众路线教育实践活动专题民主生活会时的讲话<b>★不能搞数字游戏</b>脱贫和高标准的小康是两码事。我们不是一劳永逸，毕其功于一役。相对贫困、相对落后、相对差距将长期存在。要实事求是，求真务实，踏踏实实做这个事，不能搞数字游戏。考核要有正确导向，起到促进作用。——2016年3月，在全国两会期间的讲话<b>★要有雄心壮志，更要有科学态度</b>抓工作，要有雄心壮志，更要有科学态度。一是领导工作要实，做到谋划实、推进实、作风实，求真务实，真抓实干。二是任务责任要实，做到分工实、责任实、追责实，分工明确，责任明确，履责激励，失责追究。三是资金保障要实，做到投入实、资金实、到位实，精打细算，用活用好，用在关键，用出效益。四是督查验收要实，做到制度实、规则实、监督实，加强检查，严格验收，既不拖延，也不虚报。——2016年7月20日，在银川主持召开东西部扶贫协作座谈会并发表重要讲话<b>★求真务实要经得起历史检验</b>各级领导干部是党的执政骨干，必须在“三严三实”上发挥表率作用。不能把理想信念只当口号喊，严格纪律规矩必须架起高压线，依法办事才能正确用权，求真务实要经得起历史检验。——2016年1月4日至6日，在重庆调研时的讲话大兴求真务实之风 </b><b>★立足本职、埋头苦干</b>广大青年要牢记“空谈误国、实干兴邦”，立足本职、埋头苦干，从自身做起，从点滴做起，用勤劳的双手、一流的业绩成就属于自己的人生精彩。要不怕困难、攻坚克难，勇于到条件艰苦的基层、国家建设的一线、项目攻关的前沿，经受锻炼，增长才干。——2013年5月4日，在同各界优秀青年代表座谈时的讲话<b>★唯有秉持求真务实精神，才能为社会作出更大贡献</b>做研究，就要甘于寂寞，或是皓首穷经，或是扎根实验室，“板凳要坐十年冷，文章不写一句空”。搞创作，就要坚持以人民为中心的创作思想，深入实践、深入群众、深入生活，努力创作出人民群众喜爱的精品力作。一个知识分子，不论在哪个行业、从事什么职业，也不论学历、职称、地位有多高，唯有秉持求真务实精神，才能探究更多未知，才能获得更多真理，也才能为社会作出更大贡献。——2016年4月26日，在知识分子、劳动模范、青年代表座谈会上的讲话<b>★确保各项政策百分之百落到实处</b>尽管这些问题大多处在政策执行层面，是政策执行落实不到位形成的，但影响了政策的有效性，必须下决心解决。一方面要完善政策，增强政策含金量和可操作性；另一方面要加大政策落地力度，确保各项政策百分之百落到实处。政策不落实或落实不到位、落实走样等问题，主要是“最后一公里”问题。我还是那句话，一分部署，九分落实。各地区各部门要从实际出发，细化、量化政策措施，制定相关配套举措，推动各项政策落地、落细、落实，让民营企业真正从政策中增强获得感。——2016年3月4日，参加全国政协十二届四次会议民建、工商联界委员联组会时的讲话<b>★扎实打基础，反复抓落实</b>要坚决贯彻军委关于加强作风建设的决策指示，坚持求真务实、公道正派、艰苦奋斗，对各种违规违纪问题要敢于较真碰硬、严肃查处。各级要强化强基固本思想，牢固树立大抓基层的鲜明导向，扎实打基础，反复抓落实，推动基层建设全面进步、全面过硬。——2013年1月29日，在视察武警部队时的讲话<b>用务实作风抓改革 </b><b>★强化责任担当</b>中央和国家机关有关部门是改革的责任主体，是推进改革的重要力量。各部门要坚决贯彻落实党中央决策部署，坚持以解放思想、解放和发展社会生产力、解放和增强社会活力为基本取向，强化责任担当，以自我革命的精神推进改革，坚决端正思想认识，坚持从改革大局出发，坚定抓好改革落实。——2016年10月11日，主持召开中央全面深化改革领导小组第二十八次会议并发表重要讲话<b>★确保各项改革举措落实、落细、落稳</b>要进一步抓好改革落实，加强组织领导，抓铁有痕、踏石留印，确保各项改革举措落实、落细、落稳。——2016年7月26日，在中共中央政治局第三十四次集体学习时的讲话<b>★要把“三严三实”贯穿改革全过程</b>要把“三严三实”要求贯穿改革全过程，引导广大党员、干部特别是领导干部大力弘扬实事求是、求真务实精神，理解改革要实，谋划改革要实，落实改革也要实，既当改革的促进派，又当改革的实干家。——2015年7月1日，主持召开中央全面深化改革领导小组第十四次会议并发表重要讲话<b>★增强改革落地能力</b>加强领导班子建设，必须强调求真务实，增强改革落地能力，增强领导班子的原则性，加强党风廉政建设。领导班子决策和执行的每一个环节都要实。要紧扣关键领域做好改革谋划，蹄疾步稳往前走，不能在等待观望中错失改革良机、拖延改革进程。——2015年7月16日至18日，在吉林调研时的讲话<b>★真抓实干，打赢硬仗</b>推进供给侧结构性改革，是一场硬仗。要把握好“加法”和“减法”、当前和长远、力度和节奏、主要矛盾和次要矛盾、政府和市场的关系，以锐意进取、敢于担当的精神状参加十二届全国人大四次会议湖南代表团审议（原题为《习近平用这些话，来谈务实作风》）</div>\",\"IN_DownUrl\":\"\",\"IN_DowrnNum\":0,\"IN_IsTop\":0}]";
		method.setRequestBody(listjson);

		
		client.executeMethod(method);
		
		Header[] hearders=method.getResponseHeaders();
	
		
		for(Header hea:hearders){
			System.out.println(hea.getName()+"---"+hea.getValue());
		}
		System.out.println("-------獲取返回內容------");
		System.out.println(method.getResponseBodyAsString());
		
	
	}
	
	
	public static void doGet() throws Exception{
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");

		// 创建请求方式
		GetMethod method = new GetMethod(
				"https://s.taobao.com/search?q=%E9%A9%B4%E5%BE%97%E6%B0%B4+%2B%E8%A1%A3%E6%9C%8D&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20161122&ie=utf8");

		method.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");

		client.executeMethod(method);

		System.out.println(retrieContent(method.getResponseBodyAsStream()));
	}

	public static String retrieContent(InputStream inputStream) throws Exception {
		BufferedReader in = null;

		String content = null;
		try {

			in = new BufferedReader(new InputStreamReader(inputStream));
			StringBuffer sb = new StringBuffer("");
			String line = "";
			String NL = System.getProperty("line.separator");
			while ((line = in.readLine()) != null) {
				sb.append(line + NL);
			}
			in.close();
			content = sb.toString();
		} finally {
			if (in != null) {
				try {
					in.close();// 最后要关闭BufferedReader
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return content;
		}
	}
	
	public static void executePost() throws HttpException, IOException{
		
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("UTF-8");
        
        PostMethod method=new PostMethod("http://localhost:8080/ecrm/login");
        method.addParameter("username", "rod");
        method.addParameter("password", "koala");
        
    	method.setRequestHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
		method.setRequestHeader("Cache-Control", "no-cache");
		method.setRequestHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
		
		
		client.executeMethod(method);
		Header[] hearders=method.getResponseHeaders();
		
		for(Header hea:hearders){
			System.out.println(hea.getName()+"---"+hea.getValue());
		}
		
	
	}
	public static void httpPost() throws IllegalStateException, Exception{
			HttpPost post = new HttpPost("http://localhost:8080/ecrm/login");
			 List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	            nameValuePairs.add(new BasicNameValuePair("username","rod"));
	            nameValuePairs.add(new BasicNameValuePair("password","koala"));
	            
	            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			DefaultHttpClient httpClient = new DefaultHttpClient();
			  httpClient.setRedirectStrategy(new LaxRedirectStrategy());
			 
			HttpResponse response = httpClient.execute(post);
			
			//if (response1.getStatusLine().getStatusCode() == 302) {
			//	  String redirectURL = response1.getFirstHeader("Location").getValue();
		    //}
			org.apache.http.Header[] hearders=response.getAllHeaders();
			System.out.println("Status code is:"+response.getStatusLine());
			for(org.apache.http.Header hea:hearders){
				System.out.println(hea.getName()+"---"+hea.getValue());
			}
			System.out.println(retrieContent(response.getEntity().getContent()));
			
    }
	
	public static void getLastUrl() throws IllegalStateException, Exception{
		HttpGet get = new HttpGet("http://m.baidu.com/from=0/bd_page_type=1/ssid=0/uid=0/pu=usm%400%2Csz%40224_220%2Cta%40qbase____/baiduid=6A042C8AAB993D799086439816576D16/w=0_10_%E5%BC%A0%E5%AD%A6%E5%8F%8B/t=iphone/l=1/tc?ref=www_iphone&lid=10142292730202138547&order=3&pd=realtime_wise&dict=2078&clk_info=%7B%22applid%22%3A%226239488005041754171%22%2C%22apptpl%22%3A%22normal%22%2C%22frsrcid%22%3A%2219%22%2C%22frorder%22%3A%223%22%2C%22srcid%22%3A%22200%22%2C%22tplname%22%3A%22rt_normal%22%7D&module=sf&sec=23433&di=0137ea1fa31a4c09&bdenc=1&nsrc=IlPT2AEptyoA_yixCFOxXnANedT62v3IEROF_yxZ_zqb95qshbWxBcNgJ7DNQHvTUCHbwW3CdMZQdVfqLSJvic9Ou_-ifClqlSydvOnr6xSLHxJ2tcAoFN44TWVrtOXel4Z9w12zQwBzKjYIiOCltO6vrsXWqwZekgbDsiTQgKeQYzXzZ6OIi6noNY2JXjKuO4e9d2bYnm-KGI72Bs02KJDtrmM5FDNi8xrna4JCPxXM7yUzPtzXIvROVG89QiEYShfbXOuPoKOoUyFGjycl0kRB5EzcdtCHFnVOTKe27PFCF1WHExrAS6zG0M1EcR3NK3BjX40XqNliPld7cHomEG9P2qfyBSbEYoBDQb4iuxumOExnjmSHMbmbnS2fPv37WdwV4qs2C7VKbTKlid4Vd5Dx03X1rA2qYtgu27q8--winVkxLYrysVbFdlI3diPQ4F0QRXOTQRCI3Dojjt4IQhKRRcbUESAG5SbJsCmpvpaAZsG1F4MCwotylEED4VrxhLjcSnHzGe0q");
		
		DefaultHttpClient httpClient = new DefaultHttpClient();
		  httpClient.setRedirectStrategy(new LaxRedirectStrategy());
		 
		HttpResponse response = httpClient.execute(get);
		
		//if (response1.getStatusLine().getStatusCode() == 302) {
		//	  String redirectURL = response1.getFirstHeader("Location").getValue();
	    //}
		org.apache.http.Header[] hearders=response.getAllHeaders();
		System.out.println("Status code is:"+response.getStatusLine());
		for(org.apache.http.Header hea:hearders){
			System.out.println(hea.getName()+"---"+hea.getValue());
		}
		System.out.println(retrieContent(response.getEntity().getContent()));
		
}

}
