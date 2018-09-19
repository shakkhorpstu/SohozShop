<?php

if(isset($_POST['add_product'])){
    
    try {

                if (empty($_POST['product_name'])) {
                    throw new Exception("Product name can not be empty.");
                }

                if (empty($_POST['product_price'])) {
                    throw new Exception("Product price can not be empty.");
                }

                if (empty($_POST['product_details'])) {
                    throw new Exception("Product detailscan not be empty.");
                }
                
                if (empty($_FILES['product_image'])) {
                    throw new Exception("Product image not be empty.");
                }
                
                $host = "localhost";
                $user = "root";
                $password = "";
                $database = "sohozshopping";


                try {
                    $db = new PDO("mysql:host = $host; dbname=$database", $user, $password);
                    // set the PDO error mode to exception
                    $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
                } catch (Exception $exc) {
                    echo $exc->getMessage();
                }
                
                
                $statement = $db->prepare("SHOW TABLE STATUS LIKE 'product_list'");
                $statement->execute();
                $result = $statement->fetchAll();
                foreach ($result as $row)
                    $new_id = $row[10];

                
                $target_dir = "/xampp/htdocs/admin_sohoz_shopping/uploads/";
                $file_basename = basename($_FILES["product_image"]["name"]);
                $file_ext = pathinfo($file_basename, PATHINFO_EXTENSION);
                $up_filename = $new_id .".". $file_ext;
				$img_url = "http://192.168.43.79/admin_sohoz_shopping/uploads/".$up_filename;

                if (($file_ext != "png") && ($file_ext != "jpg") && ($file_ext != "jpeg") && ($file_ext != "gif"))
                    throw new Exception("Only jpg, jpeg, png and gif format images are allowed to upload.");

                move_uploaded_file($_FILES["product_image"]["tmp_name"], $target_dir.$up_filename);

                $statement = $db->prepare("INSERT INTO product_list (product_name,product_description,product_price,product_image) VALUES (?,?,?,?)");
                $statement->execute(array($_POST['product_name'], $_POST['product_details'], $_POST['product_price'],$img_url));


                $success_message = "Product Added Successfully.";
            } catch (Exception $e) {
                $error_message = $e->getMessage();
            }
    
}

?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>add products</title>
        <link rel="stylesheet" href="admin_design.css">
    </head>
    <body>
        
        <ul style="margin-left: 400px;" id="menu">
			<li><a style="text-decoration: none" href="index.php">Add Product</a></li>
            <li><a style="text-decoration: none" href="product_list.php">Products List</a></li>
            <li><a style="text-decoration: none" href="confirm_order_list.php">Completed Order List</a></li>
            <li><a style="text-decoration: none" href="unconfirm_order_list.php">Uncomplete Order List</a></li>
        </ul>
        
        <?php
        if (isset($error_message)) {
            echo "<div class='error'>" . $error_message . "</div>";
        }
        if (isset($success_message)) {
            echo "<div class='success'>" . $success_message . "</div>";
        }
        ?>
        
        <form action="" method="post" enctype="multipart/form-data">
            <table class="edit_form">
                <tr><td>Product Name</td></tr>
                <tr><td><input style="width: 400px;" type="text" name="product_name"></td></tr>
                <tr><td>Product Details</td></tr>
                <tr><td><textarea cols="47" rows="10" type="text" name="product_details"></textarea></td></tr>
                <tr><td>Product Price</td></tr>
                <tr><td><input style="width: 400px;" type="number" name="product_price"></td></tr>
                <tr><td>Product Image</td></tr>
                <tr><td><input type="file" name="product_image"></td></tr>
                <tr><td><input style="background-color: #b9cec7;" type="submit" value="Submit" name="add_product"></td></tr>
            </table>
        </form>     
        
    </body>
</html>
