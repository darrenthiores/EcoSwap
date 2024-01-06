//
//  ReviewSheet.swift
//  EcoSwapiOS
//
//  Created by Darren Thiores on 07/01/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct ReviewSheet: View {
    let rating: Int
    @Binding var message: String
    let isLoading: Bool
    let onPickRating: (Int) -> Void
    let onUpload: () -> Void
    let reviewFor: ReviewScreen
    
    private var title: String {
        (reviewFor == .OtherProfile) ? "Review User" : "Review Store"
    }
    
    private var description: String {
        (reviewFor == .OtherProfile) ? "Write a Review on this User and share your Experience" : "Write a Review on this Bussiness and share your Experience"
    }
    
    var body: some View {
        GeometryReader { geo in
            var starSize: CGFloat {
                abs((geo.size.width - 16 - 24) / 6)
            }
            
            VStack(alignment: .center, spacing: 0) {
                ZStack {
                    Color.gray
                }
                .frame(
                    width: 56,
                    height: 4
                )
                .clipShape(
                    RoundedRectangle(cornerRadius: 32)
                )
                
                Group {
                    Spacer()
                        .frame(height: 32)
                    
                    Text(title)
                        .font(.Title2B)
                    
                    Spacer()
                        .frame(height: 8)
                    
                    Text(description)
                        .font(.SubHeadlineR)
                        .multilineTextAlignment(.center)
                    
                    Spacer()
                        .frame(height: 32)
                }
                
                HStack(spacing: 8) {
                    Spacer()
                    
                    ForEach(1...5, id: \.self) { i in
                        Button {
                            onPickRating(i)
                        } label: {
                            Image(systemName: "star.fill")
                                .resizable()
                                .scaledToFit()
                                .frame(
                                    width: starSize,
                                    height: starSize
                                )
                                .foregroundColor(
                                    ((i) <= rating) ? .Primary : .gray
                                )
                        }
                        .buttonStyle(.plain)
                    }
                    
                    Spacer()
                }
                
                Spacer()
                    .frame(height: 32)
                
                MultilineTextField(
                    text: $message,
                    placeholder: "Add your review here...",
                    lineLimit: 5,
                    reserved: true
                )
                
                Spacer()
                
                ButtonFill(
                    label: "Add Review",
                    onClick: onUpload,
                    disabled: (rating == 0 || message.isEmpty || isLoading),
                    fillWidth: true,
                    isLoading: isLoading
                )
            }
            .padding(16)
        }
    }
}

#Preview {
    ReviewSheet(
        rating: 0,
        message: .constant("awbgwaggawgbdwabduawbdawubadwhjbawdbhadwbhwadbhadwhbwadhbdwahbdbwahbdwawbgwaggawgbdwabduawbd"),
        isLoading: false,
        onPickRating: { _ in },
        onUpload: {  },
        reviewFor: .OtherProfile
    )
}
