<?php
require_once("Isendpro/autoload.php");
$api_instance = new Isendpro\Api\SmsApi();
$smsrequest = new Isendpro\Model\SmsUniqueRequest(); // \Swagger\Client\Model\SMSRequest | sms request
include("keyid.php");
$smsrequest["keyid"]="$keyid";
$smsrequest["num"]="+21698360524";
$smsrequest["sms"]="Ceci est un test avec un envoi unique à!";
//$smsrequest["ucs2"]="1";
$smsrequest["emetteur"]="iSendPro";
try {
    $result = $api_instance->sendSms($smsrequest);
    echo $result;
} catch (Exception $e) {
    echo 'Exception when calling SmsApi->sendSms: ', $e->getMessage(), PHP_EOL;
    $reponse_erreur=$e->getResponseBody();
    echo json_encode($reponse_erreur);
}
?>
