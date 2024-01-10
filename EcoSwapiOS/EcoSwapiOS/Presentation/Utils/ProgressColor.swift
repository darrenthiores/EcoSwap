//
//  ProgressColor.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 10/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension Color {
    static var Transport: Color {
        Color("ProgressColorTransport")
    }
    
    static var Energy: Color {
        Color("ProgressColorEnergy")
    }
    
    static var Challenge: Color {
        Color("ProgressColorChallenge")
    }
    
    static func getProgressColorByCategoryId(
        categoryId: String
    ) -> Color? {
        switch categoryId {
        case "1":
            return Transport
        case "2":
            return Energy
        case "3":
            return Challenge
        default:
            return nil
        }
    }
    
    static func getProgressColorByIndex(
        index: Int
    ) -> Color? {
        switch index {
        case 0:
            return Transport
        case 1:
            return Energy
        case 2:
            return Challenge
        default:
            return nil
        }
    }
    
    static var DefaultProgressColor: Color {
        Color("ProgressColorTransport")
    }
}
