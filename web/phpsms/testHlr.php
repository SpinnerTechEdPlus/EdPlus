<?php
require_once('Isendpro/autoload.php');
$api_instance = new Isendpro\Api\HlrApi();
$hlrrequest = new Isendpro\Model\HLRrequest();
$hlrrequest["get_hlr"]="1";
$hlrrequest["num"]=["0671820318"];
include("keyid.php");
$hlrrequest["keyid"]=$keyid;

try {
    $result = $api_instance->getHlr($hlrrequest);
    echo $result;
} catch (Exception $e) {
    echo 'Exception when calling HlrApi->getHlr: ', $e->getMessage(), PHP_EOL;
    $reponse_erreur=$e->getResponseBody();
    echo json_encode($reponse_erreur);
}
?>

