package com.example.springkadaiform.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactForm {

    // お名前：未入力チェック
    @NotBlank(message = "お名前を入力してください。")
    private String name;

    // メールアドレス：未入力チェック、メールアドレス形式チェック
    @NotBlank(message = "メールアドレスを入力してください。")
    @Email(message = "メールアドレスの形式が正しくありません。")
    private String email;

    // お問い合わせ内容：未入力チェック
    @NotBlank(message = "お問い合わせ内容を入力してください。")
    private String message;
}