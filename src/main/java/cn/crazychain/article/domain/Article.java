/**
 * 
 */
package cn.crazychain.article.domain;

import java.util.Date;


/**
 * @author yang
 *
 */

public class Article {
	
	   
	    private Integer id;

	    private String title;

	    private String content;
	    
	    private Date createTime;
	    
	    public void setTitle(String _title){
	    	
	    	this.title=_title;
	    }
	    
	    public String getTitle(){
	    	return this.title;
	    }
	    
	    
	  


}
