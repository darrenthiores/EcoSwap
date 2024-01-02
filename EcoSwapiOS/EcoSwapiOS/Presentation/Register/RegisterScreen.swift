//
//  RegisterScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct RegisterScreen: View {
    @StateObject private var viewModel = IosRegisterViewModel()
    @Environment(\.dismiss) private var dismiss
    
    
    var body: some View {
        ScrollView(.vertical, showsIndicators: false) {
            Image("ecoswap_logo")
                .resizable()
                .frame(
                    width: 128,
                    height: 128
                )
                .scaledToFit()
            
            Group {
                Spacer()
                    .frame(height: 24)
                
                Text("Welcome EcoSwap 👋🏻")
                    .font(.Title2B)
                
                Text("Sign up and enjoy our app")
                    .font(.SubHeadlineR)
                    .foregroundColor(.gray)
                
                Spacer()
                    .frame(height: 24)
            }
            
            Group {
                DefaultTextField(
                    text: Binding(
                        get: {
                            viewModel.state.username
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnUsernameChange(username: $0)
                            )
                        }
                    ),
                    placeholder: "Username"
                )
                
                Spacer()
                    .frame(height: 16)
                
                DefaultTextField(
                    text: Binding(
                        get: {
                            viewModel.state.email
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnEmailChange(email: $0)
                            )
                        }
                    ),
                    placeholder: "Your email"
                )
                
                Spacer()
                    .frame(height: 16)
                
                PasswordTextField(
                    text: Binding(
                        get: {
                            viewModel.state.password
                        },
                        set: {
                            viewModel.onEvent(
                                event: .OnPasswordChange(password: $0)
                            )
                        }
                    ),
                    passwordVisible: Binding(
                        get: {
                            viewModel.state.showPassword
                        },
                        set: { _ in
                            viewModel.onEvent(
                                event: .ToggleShowPassword()
                            )
                        }
                    ),
                    placeholder: "Password"
                )
                
                Spacer()
                    .frame(height: 16)
            }
            
            Group {
                Text("By continuing, you agree to out ")
                    .font(.FootnoteR)
                
                +
                
                Text("[Terms of Service](https://example.com)")
                    .font(.FootnoteB)
                
                +
                
                Text(" and ")
                    .font(.FootnoteR)
                
                +
                
                Text("[Privacy Policy](https://example.com)")
                    .font(.FootnoteB)
            }
            .multilineTextAlignment(.center)
            
            Spacer()
                .frame(height: 32)
            
            Group {
                ButtonFill(
                    label: "Create Account",
                    onClick: {
                        viewModel.onEvent(
                            event: .Register()
                        )
                    },
                    disabled: viewModel.state.isLoading || viewModel.state.username.isEmpty || viewModel.state.email.isEmpty || viewModel.state.password.isEmpty || viewModel.state.emailError != nil || viewModel.state.passwordError != nil,
                    fillWidth: true,
                    isLoading: viewModel.state.isLoading
                )
                
                Spacer()
                    .frame(height: 16)
                
                HStack(spacing: 0) {
                    Spacer()
                    
                    Text("Already Have an Account? ")
                        .font(.SubHeadlineR)
                    
                    Button {
                        dismiss()
                    } label: {
                        Text("Sign In")
                            .font(.SubHeadlineB)
                            .foregroundColor(.Blue)
                    }
                    .buttonStyle(.plain)
                    
                    Spacer()
                }
            }
            
            Spacer()
        }
        .padding(.horizontal, 24)
        .padding(.vertical, 56)
        .navigationBarBackButtonHidden()
        .onAppear {
            viewModel.startObserving()
        }
        .onDisappear {
            viewModel.dispose()
        }
    }
}

#Preview {
    RegisterScreen()
}
