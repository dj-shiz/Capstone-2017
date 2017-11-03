<?php
    $con = mysqli_connect("localhost", "android", "ohsugar", "reporter");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT username, password FROM auth_user WHERE username = ? AND password = ?") or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $username, $password);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
        $response["password"] = $password;
    }
    
    echo json_encode($response);
?>
