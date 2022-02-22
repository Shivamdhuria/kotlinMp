import SwiftUI
import common

struct DogItemView: View {

    let dog : Dog

    var body: some View {
        HStack{
            Spacer()
            VStack(){
                ImageView(withURL: dog.imageUrl, width: 300, height: 300)
                let breedName = dog.breed
                
                Text(breedName)
                    .foregroundColor(Color.gray)
                    .font(.system(size: 24, weight: .heavy))
                    .frame(width: .infinity, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                    .lineLimit(1)
                
                Text(dog.rating)
                    .foregroundColor(Color.black)
                    .font(.system(size: 18, weight: .regular))
                    .frame(width: .infinity, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
                    .lineLimit(1)
                
//                Text(dog.timestamp)
//                    .foregroundColor(Color.black)
//                    .font(.system(size: 18, weight: .regular))
//                    .frame(width: .infinity, alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/)
//                    .lineLimit(1)

            } .cornerRadius(30.0)
            Spacer()
        }
    }
}
