import Foundation

struct Images: Decodable {
    
    private var urls = [String]()
    
    init(arrayOfImgUrls: [String]) {
        self.urls = arrayOfImgUrls
    }
    
    func getImgUrlArray() -> [String] {
        return urls
    }
    
}
