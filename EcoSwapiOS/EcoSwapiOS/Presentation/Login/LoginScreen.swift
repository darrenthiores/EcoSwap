//
//  LoginScreen.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LoginScreen: View {
    @StateObject private var viewModel = IosLoginViewModel()
    
    var body: some View {
        NavigationStack {
            VStack(alignment: .center, spacing: 0) {
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
                    
                    Text("Enter your Email & Password to Sign in")
                        .font(.SubHeadlineR)
                        .foregroundColor(.gray)
                    
                    Spacer()
                        .frame(height: 24)
                }
                
                Group {
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
                
                HStack {
                    Spacer()
                    
                    Button {
                        
                    } label: {
                        Text("Forget Password?")
                            .font(.FootnoteR)
                            .foregroundColor(.Blue)
                    }
                    .buttonStyle(.plain)
                }
                
                Spacer()
                    .frame(height: 32)
                
                Group {
                    ButtonFill(
                        label: "Sign In",
                        onClick: {
                            viewModel.onEvent(
                                event: .Login()
                            )
                        },
                        disabled: viewModel.state.isLoading || viewModel.state.email.isEmpty || viewModel.state.password.isEmpty,
                        fillWidth: true,
                        isLoading: viewModel.state.isLoading
                    )
                    
                    Spacer()
                        .frame(height: 16)
                    
                    HStack(spacing: 0) {
                        Spacer()
                        
                        Text("Don’t Have an Account? ")
                            .font(.SubHeadlineR)
                        
                        NavigationLink {
                            RegisterScreen()
                        } label: {
                            Text("Sign Up")
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
            .onAppear {
                viewModel.startObserving()
            }
            .onDisappear {
                viewModel.dispose()
            }
        }
    }
}

#Preview {
    LoginScreen()
}
