<?php
include_once './database_file.php';
?>

<?php
if (!isset($_REQUEST['id'])) {
    header("location: product_list.php");
} else {
    $id = $_REQUEST['id'];
}
$image = $id.'.jpg';
?>

<?php

    $statement = $db->prepare("SELECT * FROM product_list WHERE id=?");
    $statement->execute(array($id));
    $result = $statement->fetchAll(PDO::FETCH_ASSOC);
    foreach($result as $row)
    {
            $real_path = "uploads/".$image;
            unlink($real_path);
    }

    $statement = $db->prepare("DELETE FROM product_list WHERE id = ?");
    $statement->execute(array($id));
    header("location: edit_product.php");


?>

