<?php
include_once './database_file.php';
?>

<?php
if (!isset($_REQUEST['id'])) {
    header("location: customer_shopping.php");
} else {
    $id = $_REQUEST['id'];
}
?>

<?php
    $statement = $db->prepare("DELETE FROM customer_shopping WHERE id = ?");
    $statement->execute(array($id));
    header("location: confirm_order_list.php");
?>