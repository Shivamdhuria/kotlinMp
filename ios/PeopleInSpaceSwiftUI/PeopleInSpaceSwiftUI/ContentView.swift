import SwiftUI
import Combine
import common


struct ContentView: View {
//    @StateObject var viewModel = PeopleInSpaceViewModel(repository: PeopleInSpaceRepository())
    @ObservedObject var viewModel: DogListViewModel
    init(getDog : GetDogs) {
           viewModel = DogListViewModel(getDogs: getDog)
       }
    
    
    var body: some View {
        
        VStack{
            NavigationView {
                List(viewModel.dogs, id: \.self) { dog in
                    DogItemView(dog: dog).padding(.bottom, 20)
                }.navigationBarTitle("Dogs")
                
            }
            switch viewModel.state {
            case .loading: ProgressView()
            case .failed(let errorMessage):ErrorView(error: errorMessage, handler: viewModel.loadDogs)
            case .success:
                    Spacer()
                    Button (action : viewModel.loadDogs ,label :{Text("Load more ")})

            }
        }.onAppear(perform: viewModel.loadDogs)
    }


//    var body: some View {
//        TabView {
//            PeopleListView(viewModel: viewModel)
//                .tabItem {
//                    Label("People", systemImage: "person")
//                }
//            ISSMapView(issPosition: $viewModel.issPosition)
//                .tabItem {
//                    Label("Map", systemImage: "location")
//                }
//        }
//    }
}

//struct PeopleListView: View {
//    @ObservedObject var viewModel: PeopleInSpaceViewModel
//
//    var body: some View {
//        NavigationView {
//            List(viewModel.people, id: \.name) { person in
//                NavigationLink(destination: PersonDetailsView(viewModel: viewModel, person: person)) {
//                    PersonView(viewModel: viewModel, person: person)
//                }
//            }
//            .navigationBarTitle(Text("People In Space"))
//            .onAppear {
//                viewModel.startObservingPeopleUpdates()
//            }.onDisappear {
//                viewModel.stopObservingPeopleUpdates()
//            }
//        }
//    }
//}
//
//struct PersonView: View {
//    var viewModel: PeopleInSpaceViewModel
//    var person: Assignment
//
//    var body: some View {
//        HStack {
//            ImageView(withURL: person.personImageUrl ?? "", width: 64, height: 64)
//            VStack(alignment: .leading) {
//                Text(person.name).font(.headline)
//                Text(person.craft).font(.subheadline)
//            }
//        }
//    }
//}
//
//
//struct PersonDetailsView: View {
//    var viewModel: PeopleInSpaceViewModel
//    var person: Assignment
//
//    var body: some View {
//        ScrollView {
//            VStack(alignment: .center, spacing: 32) {
//                Text(person.name).font(.title)
//
//                ImageView(withURL: person.personImageUrl ?? "", width: 240, height: 240)
//
//                Text(person.personBio ?? "").font(.body)
//                Spacer()
//            }
//            .padding()
//        }
//    }
//}
//
//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
