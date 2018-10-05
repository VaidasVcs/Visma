import Foundation

struct Image {
    
    private let url: String
    
    init?(url: String) {
        self.url = url
    }
    
    func getUrl() -> String {
        return url
    }
}
