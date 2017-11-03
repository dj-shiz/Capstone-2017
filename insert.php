<?php
    $con = mysqli_connect("localhost", "android", "ohsugar", "reporter");
    
    $title = $_POST["title"];
    $desc = $_POST["desc"];
    $priority = $_POST["priority"];
    $username = $_POST["username"];
        
    $statement = mysqli_prepare($con, "INSERT INTO incident_log (title, description, timestamp, priority, user_id) VALUES (?, ?, NOW(), ?, (SELECT id FROM auth_user WHERE username = ?))") or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "ssis", $title, $desc, $priority, $username);
    mysqli_stmt_execute($statement); 
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
