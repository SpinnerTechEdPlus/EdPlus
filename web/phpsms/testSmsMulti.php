<?php
require_once("Isendpro/autoload.php");
$api_instance = new Isendpro\Api\SmsApi();
$smsrequest = new Isendpro\Model\SMSRequest(); // \Swagger\Client\Model\SMSRequest | sms request
include("keyid.php");
$smsrequest["keyid"]=$keyid;
$smsrequest["num"]=["0695208432","0600123456"];
$smsrequest["sms"]=["Ceci est un test avec un envoi multiple éàè!"]; // 1 message ou autant de message que de destinataires
$smsrequest["emetteur"]="iSendPro";
try {
    $result = $api_instance->sendSmsMulti($smsrequest);
    echo $result;
} catch (Exception $e) {
    echo 'Exception when calling SmsApi->sendSms: ',print_r($e), PHP_EOL;
    $reponse_erreur=$e->getResponseBody();
    echo json_encode($reponse_erreur);
}
?>
