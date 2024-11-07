<?php

use PHPUnit\Framework\TestCase;

require_once 'C:/Users/User-Hp/Documents/project/phpproject/ValidatePassword.php';

class ValidatePasswordTest extends TestCase{

    public function testValidateLength(){
        $valPass  = new ValidatePassword();
        $this->assertFalse($valPass->validLength('1234'));
    }
}