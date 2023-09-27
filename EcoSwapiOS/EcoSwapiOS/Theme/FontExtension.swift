//
//  FontExtension.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 27/09/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

extension Font {
    private static let sfProDisplayRegular = "SFProDisplay-Regular"
    private static let sfProDisplayMedium = "SFProDisplay-Medium"
    private static let sfProDisplayBold = "SFProDisplay-Bold"
    
    // regular
    static var LargeTitleR: Font {
        Font.custom(sfProDisplayRegular, size: 34)
    }
    
    static var Title1R: Font {
        Font.custom(sfProDisplayRegular, size: 28)
    }
    
    static var Title2R: Font {
        Font.custom(sfProDisplayRegular, size: 22)
    }
    
    static var Title3R: Font {
        Font.custom(sfProDisplayRegular, size: 20)
    }
    
    static var HeadlineR: Font {
        Font.custom(sfProDisplayMedium, size: 17)
    }
    
    static var BodyR: Font {
        Font.custom(sfProDisplayRegular, size: 17)
    }
    
    static var CalloutR: Font {
        Font.custom(sfProDisplayRegular, size: 16)
    }
    
    static var SubHeadlineR: Font {
        Font.custom(sfProDisplayRegular, size: 15)
    }
    
    static var FootnoteR: Font {
        Font.custom(sfProDisplayRegular, size: 13)
    }
    
    static var Caption1R: Font {
        Font.custom(sfProDisplayRegular, size: 12)
    }
    
    static var Caption2R: Font {
        Font.custom(sfProDisplayRegular, size: 11)
    }
    
    // bold
    static var LargeTitleB: Font {
        Font.custom(sfProDisplayBold, size: 34)
    }
    
    static var Title1B: Font {
        Font.custom(sfProDisplayBold, size: 28)
    }
    
    static var Title2B: Font {
        Font.custom(sfProDisplayBold, size: 22)
    }
    
    static var Title3B: Font {
        Font.custom(sfProDisplayMedium, size: 20)
    }
    
    static var HeadlineB: Font {
        Font.custom(sfProDisplayMedium, size: 17)
    }
    
    static var BodyB: Font {
        Font.custom(sfProDisplayMedium, size: 17)
    }
    
    static var CalloutB: Font {
        Font.custom(sfProDisplayMedium, size: 16)
    }
    
    static var SubHeadlineB: Font {
        Font.custom(sfProDisplayMedium, size: 15)
    }
    
    static var FootnoteB: Font {
        Font.custom(sfProDisplayMedium, size: 13)
    }
    
    static var Caption1B: Font {
        Font.custom(sfProDisplayMedium, size: 12)
    }
    
    static var Caption2B: Font {
        Font.custom(sfProDisplayMedium, size: 11)
    }
}
