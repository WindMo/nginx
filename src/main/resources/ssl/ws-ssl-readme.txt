服务端证书：
口令：123456server
keytool -genkey -alias ws-ssl-server -storetype JKS -keyalg RSA  -validity 9999 -keystore ws-ssl-server.jks

keytool -keystore ws-ssl-server.jks -export -alias ws-ssl-server -file ws-ssl-server.cer

客户端证书：
口令：123456client
keytool -genkey -alias ws-ssl-client -storetype JKS -keyalg RSA  -validity 9999 -keystore ws-ssl-client.jks

keytool -keystore ws-ssl-client.jks -export -alias ws-ssl-client -file ws-ssl-client.cer

客户端cer导入服务端密钥库
keytool -import -alias ws-ssl-client -file ws-ssl-client.cer -keystore ws-ssl-server.jks

服务端cer导入客户端密钥库
keytool -import -alias ws-ssl-server -file ws-ssl-server.cer -keystore ws-ssl-client.jks

keytool -importkeystore -srckeystore ws-ssl-client.jks -destkeystore ws-ssl-client.jks -deststoretype pkcs12