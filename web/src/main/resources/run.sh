#!/bin/sh
#�ýű�ΪLinux������java�����ͨ�ýű�����������Ϊ����������service�ű������ã�
#Ҳ������Ϊ����java����Ķ����ű���ʹ�á�
#
#
#����!!!���ýű�stop����ʹ��ϵͳkill������ǿ����ָֹ����java������̡�
#��ɱ������ǰ��δ���κ�������顣��ĳЩ����£���������ڽ����ļ������ݿ�д������
#���ܻ�������ݶ�ʧ�����ݲ��������������Ҫ���ǵ��������������Ҫ��д�˽ű���
#������ִ��kill����ǰ��һϵ�м�顣
#
#
###################################
#��������������ִ�в���
#��Ҫ����ʵ�ʻ����Լ�Java�����������޸���Щ����
###################################
#JDK����·��
JAVA_HOME="/data1/jdk1.8.0_91"
 
#Java�������ڵ�Ŀ¼��classes����һ��Ŀ¼��
APP_HOME=/data1/woodpecker/woodpecker.web-1.0-SNAPSHOT.jar
 
#java�������������
JAVA_OPTS="-server  -Xms3g -Xmx3g -Xmn1g -Xss256k -XX:PermSize=256M -XX:MaxPermSize=512M -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=5
-XX:GCTimeRatio=19 -Xnoclassgc -XX:+DisableExplicitGC -XX:+UseParNewGC -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection
-XX:CMSFullGCsBeforeCompaction=0 -XX:-CMSParallelRemarkEnabled -XX:CMSInitiatingOccupancyFraction=70 -XX:SoftRefLRUPolicyMSPerMB=0
-XX:+PrintClassHistogram -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintHeapAtGC -Xloggc:/data1/woodpecker/gc.log";

###################################
#(����)�жϳ����Ƿ�������
#
#˵����
#ʹ��JDK�Դ���JPS���grep������ϣ�׼ȷ����pid
#jps �� l ��������ʾ��ʾjava��������·��
#ʹ��awk���ָ��pid ($1����)����Java��������($2����)
###################################
#��ʼ��psid������ȫ�֣�
psid=0
 
checkpid() {
   javaps=`$JAVA_HOME/bin/jps -l | grep $APP_HOME`
 
   if [ -n "$javaps" ]; then
      psid=`echo $javaps | awk '{print $1}'`
   else
      psid=0
   fi
}
 
###################################
#(����)��������
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0��������ʾ����������
#3. �������û�б���������ִ������������
#4. ��������ִ�к��ٴε���checkpid����
#5. �������4�Ľ���ܹ�ȷ�ϳ����pid,���ӡ[OK]�������ӡ[Failed]
#ע�⣺echo -n ��ʾ��ӡ�ַ��󣬲�����
#ע��: "nohup ĳ���� >/dev/null 2>&1 &" ���÷� 
###################################
start() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo "================================"
      echo "warn: $APP_HOME already started! (pid=$psid)"
      echo "================================"
   else
      echo -n "Starting $APP_HOME ..."
     nohup $JAVA_HOME/bin/java $JAVA_OPTS -jar  $APP_HOME >/dev/null 2>&1 &
      checkpid
      if [ $psid -ne 0 ]; then
         echo "(pid=$psid) [OK]"
      else
         echo "[Failed]"
      fi
   fi
}
 
###################################
#(����)ֹͣ����
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0������ʼִ��ֹͣ��������ʾ����δ����
#3. ʹ��kill -9 pid�������ǿ��ɱ������
#4. ִ��kill�����н���������ϲ鿴��һ������ķ���ֵ: $?
#5. �������4�Ľ��$?����0,���ӡ[OK]�������ӡ[Failed]
#6. Ϊ�˷�ֹjava����������Σ��������ӷ��������̣�����ɱ���Ĵ����ݹ����stop����
#ע�⣺echo -n ��ʾ��ӡ�ַ��󣬲�����
#ע��: ��shell����У�"$?" ��ʾ��һ���������һ�������ķ���ֵ
###################################
stop() {
   checkpid
 
   if [ $psid -ne 0 ]; then
      echo -n "Stopping $APP_HOME ...(pid=$psid) "
      
       checkpid
       if [ $psid -ne 0 ]; then
		      kill -15 $psid
		      sleep 20
		       echo  " kill over...sleep 20s "
		       kill -9 $psid
		      if [ $? -eq 0 ]; then
		         echo "kill [OK],APP is shutdowned "
		      else
		        echo "kill -15 [Failed],execute kill -9 "
		        sleep 10
		        kill -9 $psid
		      fi
		else
		      echo "================================"
		      echo  "no kill,APP is shutdowned "
		      echo "================================"
   		fi

   else
      echo "================================"
      echo "warn: $APP_HOME is not running"
      echo "================================"
   fi
}
 
###################################
#(����)����������״̬
#
#˵����
#1. ���ȵ���checkpid������ˢ��$psidȫ�ֱ���
#2. ��������Ѿ�������$psid������0��������ʾ�������в���ʾ��pid
#3. ������ʾ����δ����
###################################
status() {
   checkpid
 
   if [ $psid -ne 0 ];  then
      echo "$APP_HOME is running! (pid=$psid)"
   else
      echo "$APP_HOME is not running"
   fi
}
 
###################################
#(����)��ӡϵͳ��������
###################################
info() {
   echo "System Information:"
   echo "****************************"
   echo `head -n 1 /etc/issue`
   echo `uname -a`
   echo
   echo "JAVA_HOME=$JAVA_HOME"
   echo `$JAVA_HOME/bin/java -version`
   echo
   echo "APP_HOME=$APP_HOME"
   echo "****************************"
}
 
###################################
#��ȡ�ű��ĵ�һ������($1)�������ж�
#����ȡֵ��Χ��{start|stop|restart|status|info}
#���������ָ����Χ֮�ڣ����ӡ������Ϣ
###################################
case "$1" in
   'start')
      start
      ;;
   'stop')
     stop
     ;;
   'restart')
     stop
     start
     ;;
   'status')
     status
     ;;
   'info')
     info
     ;;
  *)
echo "Usage: $0 {start|stop|restart|status|info}"
exit 1
esac 
exit 0
