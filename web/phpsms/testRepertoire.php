<?php
require_once('Isendpro/autoload.php');
include("keyid.php");
$api_instance = new Isendpro\Api\RepertoireApi();
$repertoirecreaterequest = new Isendpro\Model\REPERTOIREcreaterequest();
$repertoirecreaterequest["keyid"]=$keyid;
$repertoirecreaterequest["repertoire_edit"]="create";
$repertoirecreaterequest["repertoire_nom"]="Prospects interessants";
$id_nouveau_repertoire="";
try {
    $result = $api_instance->repertoireCrea($repertoirecreaterequest);
    echo $result;
    $id_nouveau_repertoire=$result["etat"]["etat"][0]["repertoire_id"];
} catch (Exception $e) {
    echo 'Exception when calling RepertoireApi->repertoireCrea: ', $e->getMessage(), PHP_EOL;
    $reponse_erreur=$e->getResponseBody();
    echo json_encode($reponse_erreur);
}
echo "nouveaurepertoire=".$id_nouveau_repertoire;
$api_instance = new Isendpro\Api\RepertoireApi();
$repertoiremodifrequest = new Isendpro\Model\REPERTOIREmodifrequest(); 

$repertoiremodifrequest["keyid"]=$keyid;
$repertoiremodifrequest["repertoire_edit"]="add";
$repertoiremodifrequest["repertoire_id"]=$id_nouveau_repertoire;
$repertoiremodifrequest["num"]=["0612345600","061200345678","0600123456"];
try {
    $result = $api_instance->repertoire($repertoiremodifrequest);
    echo $result;
} catch (Exception $e) {
    echo 'Exception when calling RepertoireApi->repertoireCrea: ', $e->getMessage(), PHP_EOL;
    $reponse_erreur=$e->getResponseBody();
    echo json_encode($reponse_erreur);
}
?>

