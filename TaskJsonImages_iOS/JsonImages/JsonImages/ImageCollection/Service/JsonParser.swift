import Foundation

class JsonParser {
    private init(){}
    
    static func parseJson(url: URL, completion: @escaping (GetJsonParserResponse)->Void) {
        URLSession.shared.dataTask(with: url) {(data, response, error) in
            guard let data = data else { return }
            do {
                let imagesObj = try JSONDecoder().decode(Images.self, from: data)
                let response = GetJsonParserResponse(imagesUrlList: imagesObj.getImgUrlArray())
                DispatchQueue.main.async {
                    completion(response)
                }
                
            } catch {
                print("we got an error")
            }
            
            }.resume()
    }
}
