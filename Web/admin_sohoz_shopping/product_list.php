<html>
    <title>product list</title>
    <head>
        <link rel="stylesheet" href="admin_design.css">
         <script>
            function confirm_delete(){
                return confirm("Are you want to delete this data?")
            }
        </script>
    </head>
    
    <body>
        <ul style="margin-left: 400px;" id="menu">
            <li><a style="text-decoration: none" href="index.php">Add Product</a></li>
			<li><a style="text-decoration: none" href="product_list.php">Products List</a></li>
            <li><a style="text-decoration: none" href="confirm_order_list.php">Completed Order List</a></li>
            <li><a style="text-decoration: none" href="unconfirm_order_list.php">Uncomplete Order List</a></li>
        </ul>
        <div style="margin-left: 300px;">
            <table class="product_list">
            <tr>
                <td width="10%">Serial</td>
                <td width="30%">Products Name</td>
                <td width="15%">Products Price</td>
                <td width="10%">Action</td>
            </tr>
            
            <?php
            include_once 'database_file.php';
            $i = 0;
            $statement = $db->prepare("SELECT * FROM product_list ORDER BY id DESC");
            $statement->execute();
            $result = $statement->fetchAll(PDO::FETCH_ASSOC);
            foreach ($result as $row) {
                $i++;
                ?>
            
            <tr class="product">
                <td><?php echo $i; ?></td>
                <td><?php echo $row['product_name']; ?></td>
                <td><?php echo $row['product_price']; ?></td>
                <td><a class="edit" href="edit_product.php?id=<?php echo $row['id']; ?>">Edit</a>&nbsp;|
                    <a class="delete" onclick="return confirm_delete();"  href="delete_product.php?id=<?php echo $row['id']; ?>">Delete</td>
            </tr>
            <?php
            }
            ?>
        </table>
		</div>
        
    </body>
    
</html>

