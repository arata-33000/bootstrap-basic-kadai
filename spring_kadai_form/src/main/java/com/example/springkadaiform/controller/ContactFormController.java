package com.example.springkadaiform.controller;

import org.springframework.core.Conventions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.springkadaiform.form.ContactForm;

@Controller
public class ContactFormController {

	//お問い合わせフォーム画面を表示する
    @GetMapping("/form")
    public String showForm(Model model) {
        // 14章：すでにインスタンス（入力途中のデータ）が存在する場合は上書きしない
        if (!model.containsAttribute("contactForm")) {
            model.addAttribute("contactForm", new ContactForm());
        }
        return "contactFormView";
    }

    //入力内容のバリデーションを行い、結果に応じて遷移先を切り替える
    @PostMapping("/confirm")
    public String confirm(RedirectAttributes redirectAttributes,
                          @Validated ContactForm contactForm,
                          BindingResult result,
                          Model model) {

        // 15章：バリデーションエラーがあった場合の処理
        if (result.hasErrors()) {
            // 入力途中のフォームオブジェクトをフラッシュ属性として渡す
            redirectAttributes.addFlashAttribute("contactForm", contactForm);
            
            // バリデーション結果（エラー内容）をフラッシュ属性として渡す
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX
                    + Conventions.getVariableName(contactForm), result);

            // 合格基準に合わせ、/formへリダイレクト
            return "redirect:/form";
        }

        // バリデーションエラーがなければ、確認画面へデータを渡して表示
        model.addAttribute("contactForm", contactForm);
        return "confirmView";
    }
}