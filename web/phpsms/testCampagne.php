<?php
require_once("keyid.php");
require_once("Isendpro/autoload.php");
$api_instance = new Isendpro\Api\CampagneApi();
$rapport_campagne = "1"; // string |
$date_deb = "2017-07-01 00:00"; // string | date de debut au format YYYY-MM-DD hh:mm
$date_fin = "2017-07-13 23:00"; // string | date de fin au format YYYY-MM-DD hh:mm

try {
    $result = $api_instance->getCampagne($keyid, $rapport_campagne, $date_deb, $date_fin);
    print_r($result);
    echo $result->getRealPath();
} catch (Exception $e) {
    echo 'Exception when calling CampagneApi->getCampagne: ', $e->getMessage(), PHP_EOL;
}
?>

