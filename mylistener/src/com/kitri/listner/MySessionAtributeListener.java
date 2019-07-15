package com.kitri.listner;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class MySessionAtributeListener implements HttpSessionAttributeListener {

    //로그인된 사용자가 몇명인지 알고싶음.
	private int loginCnt;
	
    public MySessionAtributeListener() {
    	
    }

	
    public void attributeAdded(HttpSessionBindingEvent e)  { 
    	String attName = e.getName();
    	if(attName.equals("loginInfo")) {
    		loginCnt++;
    		System.out.println(e.getValue()+"님이 로그인하셨습니다!");
    		System.out.println("로그인된 사용자수:"+loginCnt+"명");
    	}
    }

	
    public void attributeRemoved(HttpSessionBindingEvent e)  { 
    	String attName = e.getName();
    	if(attName.equals("loginInfo")) {
    		loginCnt--;
    		System.out.println(e.getValue()+"님이 로그아웃하셨습니다!");
    		System.out.println("로그인된 사용자수:"+loginCnt+"명");
    	}
    }

	
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    	
    }
	
}
