import Foundation

class GetJsonParserResponse {
    
    private let urls: [String]
    private var imageList: [Image] = []
    
    init(imagesUrlList: [String]) {
        self.urls = imagesUrlList
        self.imageList = populateImagesWithUrls()
    }
    
   private func populateImagesWithUrls() -> [Image] {
        var imgList = [Image]()
        for url in urls {
            guard let image = Image(url: url) else { continue }
            imgList.append(image)
        }
        return imgList
    }
    
    func getImgList() -> [Image] {
        return self.imageList
    }
    
}
