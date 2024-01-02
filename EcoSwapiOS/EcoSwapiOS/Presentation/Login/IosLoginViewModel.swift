//
//  IosLoginViewModel.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 02/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension LoginScreen {
    @MainActor class IosLoginViewModel: ObservableObject {
        private let viewModel: LoginViewModel
        
        @Published var state: LoginState = LoginState(
            email: "",
            password: "",
            showPassword: false,
            isLoading: false,
            error: nil,
            loginSuccess: false
        )
        
        private var handle: DisposableHandle?
        
        init() {
            @Inject var login: Login
            
            self.viewModel = LoginViewModel(
                login: login,
                coroutineScope: nil
            )
        }
        
        func onEvent(event: LoginEvent) {
            viewModel.onEvent(event: event)
        }
        
        func startObserving() {
            handle = viewModel.state.subscribe { state in
                if let state = state {
                    self.state = state
                    
                    if state.loginSuccess {
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
