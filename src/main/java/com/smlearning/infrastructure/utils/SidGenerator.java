package com.smlearning.infrastructure.utils;

public class SidGenerator {

  
  private static char[] SID_CH=new char[26+10];
  static{
      for(char c='A';c<='Z';c++)
          SID_CH[c-'A']=c;
      for(char c='0';c<='9';c++)
          SID_CH[c-'0'+26]=c;     
  }
  public static final int SID_LEN=18;
  /**
   * 随机产生一个包括大写字母和数字的长度为18的字符串，以prefix开头
   * @param preffix:
   *          字符串前缀
   */
  public static String genSid(char preffix) {
      StringBuffer buf=new StringBuffer();
      buf.append(preffix);
      for(int i=1;i<SID_LEN;i++){
          int index=(int)(Math.random()*SID_CH.length);
          buf.append(SID_CH[index]);
      }
      return buf.toString();
  }
  public static String genVerifyCode(int len) {
      StringBuffer buf=new StringBuffer();
      for(int i=0;i<len;i++){
          int index=(int)(Math.random()*SID_CH.length);
          buf.append(SID_CH[index]);
      }

      return buf.toString();
  }   

}
