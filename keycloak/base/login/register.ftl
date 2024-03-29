<#import "template.ftl" as layout>
<@layout.registrationLayout displayMessage=!messagesPerField.existsError('firstName','lastName','email','username','password','password-confirm'); section>
    <#if section = "header">
        ${msg("registerTitle")}
    <#elseif section = "form">
        <form id="kc-register-form" class="${properties.kcFormClass!}" action="${url.registrationAction}" method="post">
            <div class="${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="firstName" class="${properties.kcLabelClass!}">${msg("firstName")}</label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" id="firstName" class="${properties.kcInputClass!}" name="firstName"
                           value="${(register.formData.firstName!'')}"
                           aria-invalid="<#if messagesPerField.existsError('firstName')>true</#if>"
                    />

                    <#if messagesPerField.existsError('firstName')>
                        <span id="input-error-firstname" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                            ${kcSanitize(messagesPerField.get('firstName'))?no_esc}
                        </span>
                    </#if>
                </div>
            </div>

            <div class="${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="lastName" class="${properties.kcLabelClass!}">${msg("lastName")}</label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" id="lastName" class="${properties.kcInputClass!}" name="lastName"
                           value="${(register.formData.lastName!'')}"
                           aria-invalid="<#if messagesPerField.existsError('lastName')>true</#if>"
                    />

                    <#if messagesPerField.existsError('lastName')>
                        <span id="input-error-lastname" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                            ${kcSanitize(messagesPerField.get('lastName'))?no_esc}
                        </span>
                    </#if>
                </div>
            </div>

            <div class="form-group">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.street" class="${properties.kcLabelClass!}">
                    Address</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.street" name="user.attributes.street" 
                    value="${(register.formData['user.attributes.street']!'')}"/>
                </div>

                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.city" class="${properties.kcLabelClass!}">
                    City</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.city" name="user.attributes.city" 
                    value="${(register.formData['user.attributes.city']!'')}"/>
                </div>

                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.country" class="${properties.kcLabelClass!}">
                    Country</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.country" name="user.attributes.country" 
                    value="${(register.formData['user.attributes.country']!'')}"/>
                </div>

                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.phoneNumber" class="${properties.kcLabelClass!}">
                    Phone Number</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.phoneNumber" name="user.attributes.phoneNumber" 
                    value="${(register.formData['user.attributes.phoneNumber']!'')}"/>
                </div>


                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.jmbg" class="${properties.kcLabelClass!}">
                    JMBG</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.jmbg" name="user.attributes.jmbg" 
                    value="${(register.formData['user.attributes.jmbg']!'')}"/>
                </div>


                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.gender" class="${properties.kcLabelClass!}">
                    Gender</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <select type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.gender" name="user.attributes.gender" 
                    value="${(register.formData['user.attributes.gender']!'')}">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>
                </div>

                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.profession" class="${properties.kcLabelClass!}">
                    Profession</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.profession" name="user.attributes.profession" 
                    value="${(register.formData['user.attributes.profession']!'')}"/>
                </div>

                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="user.attributes.professionInfo" class="${properties.kcLabelClass!}">
                    Company/School</label>
                </div>

                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" class="${properties.kcInputClass!}" 
                    id="user.attributes.professionInfo" name="user.attributes.professionInfo" 
                    value="${(register.formData['user.attributes.professionInfo']!'')}"/>
                </div>
                
            </div>


            <div class="${properties.kcFormGroupClass!}">
                <div class="${properties.kcLabelWrapperClass!}">
                    <label for="email" class="${properties.kcLabelClass!}">${msg("email")}</label>
                </div>
                <div class="${properties.kcInputWrapperClass!}">
                    <input type="text" id="email" class="${properties.kcInputClass!}" name="email"
                           value="${(register.formData.email!'')}" autocomplete="email"
                           aria-invalid="<#if messagesPerField.existsError('email')>true</#if>"
                    />

                    <#if messagesPerField.existsError('email')>
                        <span id="input-error-email" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                            ${kcSanitize(messagesPerField.get('email'))?no_esc}
                        </span>
                    </#if>
                </div>
            </div>

            <#if !realm.registrationEmailAsUsername>
                <div class="${properties.kcFormGroupClass!}">
                    <div class="${properties.kcLabelWrapperClass!}">
                        <label for="username" class="${properties.kcLabelClass!}">${msg("username")}</label>
                    </div>
                    <div class="${properties.kcInputWrapperClass!}">
                        <input type="text" id="username" class="${properties.kcInputClass!}" name="username"
                               value="${(register.formData.username!'')}" autocomplete="username"
                               aria-invalid="<#if messagesPerField.existsError('username')>true</#if>"
                        />

                        <#if messagesPerField.existsError('username')>
                            <span id="input-error-username" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                                ${kcSanitize(messagesPerField.get('username'))?no_esc}
                            </span>
                        </#if>
                    </div>
                </div>
            </#if>

            <#if passwordRequired??>
                <div class="${properties.kcFormGroupClass!}">
                    <div class="${properties.kcLabelWrapperClass!}">
                        <label for="password" class="${properties.kcLabelClass!}">${msg("password")}</label>
                    </div>
                    <div class="${properties.kcInputWrapperClass!}">
                        <input type="password" id="password" class="${properties.kcInputClass!}" name="password"
                               autocomplete="new-password"
                               aria-invalid="<#if messagesPerField.existsError('password','password-confirm')>true</#if>"
                        />

                        <#if messagesPerField.existsError('password')>
                            <span id="input-error-password" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                                ${kcSanitize(messagesPerField.get('password'))?no_esc}
                            </span>
                        </#if>
                    </div>
                </div>

                <div class="${properties.kcFormGroupClass!}">
                    <div class="${properties.kcLabelWrapperClass!}">
                        <label for="password-confirm"
                               class="${properties.kcLabelClass!}">${msg("passwordConfirm")}</label>
                    </div>
                    <div class="${properties.kcInputWrapperClass!}">
                        <input type="password" id="password-confirm" class="${properties.kcInputClass!}"
                               name="password-confirm"
                               aria-invalid="<#if messagesPerField.existsError('password-confirm')>true</#if>"
                        />

                        <#if messagesPerField.existsError('password-confirm')>
                            <span id="input-error-password-confirm" class="${properties.kcInputErrorMessageClass!}" aria-live="polite">
                                ${kcSanitize(messagesPerField.get('password-confirm'))?no_esc}
                            </span>
                        </#if>
                    </div>
                </div>
            </#if>

            <#if recaptchaRequired??>
                <div class="form-group">
                    <div class="${properties.kcInputWrapperClass!}">
                        <div class="g-recaptcha" data-size="compact" data-sitekey="${recaptchaSiteKey}"></div>
                    </div>
                </div>
            </#if>

            <div class="${properties.kcFormGroupClass!}">
                <div id="kc-form-options" class="${properties.kcFormOptionsClass!}">
                    <div class="${properties.kcFormOptionsWrapperClass!}">
                        <span><a href="${url.loginUrl}">${kcSanitize(msg("backToLogin"))?no_esc}</a></span>
                    </div>
                </div>

                <div id="kc-form-buttons" class="${properties.kcFormButtonsClass!}">
                    <input class="${properties.kcButtonClass!} ${properties.kcButtonPrimaryClass!} ${properties.kcButtonBlockClass!} ${properties.kcButtonLargeClass!}" type="submit" value="${msg("doRegister")}"/>
                </div>
            </div>
        </form>
    </#if>
</@layout.registrationLayout>