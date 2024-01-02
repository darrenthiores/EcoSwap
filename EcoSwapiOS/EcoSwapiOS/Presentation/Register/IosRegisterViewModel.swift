//
//  IosRegisterViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension RegisterScreen {
    @MainActor class IosRegisterViewModel: ObservableObject {
        private let viewModel: RegisterViewModel
        
        @Published var state: RegisterState = RegisterState(
            username: "",
            email: "",
            emailError: nil,
            password: "",
            passwordError: nil,
            showPassword: false,
            isLoading: false,
            error: nil,
            registerSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var register: Register
            @Inject var validateEmail: ValidateEmail
            @Inject var validatePassword: ValidatePassword
            
            self.viewModel = RegisterViewModel(
                register: register,
                validateEmail: validateEmail,
                validatePassword: validatePassword,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: RegisterEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.registerSuccess {
                        let userDefaults = UserDefaults.standard
                        userDefaults.set(true, forKey: "isLogin")
                    }
                }
            }
        }
        
        func dispose() {
            handle?.dispose()
        }
    }
}
