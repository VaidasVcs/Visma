import UIKit

class CollectionViewController: UIViewController {
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    private var imageList = [Image]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        loadCollectionView(url: getLocalJson(fileName: "dog_urls"))
        }
    
   private func loadCollectionView(url: URL) {
        JsonParser.parseJson(url: url) {(response) in
            let imageList = response.getImgList()
            self.imageList = imageList
            DispatchQueue.main.async {
                self.collectionView.reloadData()
            }
            
        }
    }
        
    private func getLocalJson(fileName json: String) -> URL {
            return URL.init(fileURLWithPath: Bundle.main.path(forResource: json, ofType: ".json") ?? "")
        }
    
}


extension CollectionViewController: UICollectionViewDataSource, UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
       return imageList.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "imageCell", for: indexPath) as! ImageViewCell
        if let imageUrl = URL(string: imageList[indexPath.row].getUrl()) {
            loadImage(imageUrl: imageUrl, imageCell: cell)
        }
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let destinatioViewController = storyboard?.instantiateViewController(withIdentifier: "ImageViewController") as! ImageViewController
        if let imageUrl = URL(string: imageList[indexPath.row].getUrl()) {
            loadImage(imageUrl: imageUrl, controller: destinatioViewController)
        }
        self.navigationController?.pushViewController(destinatioViewController, animated: true)
    }
    
    
    private func loadImage(imageUrl: URL, imageCell: ImageViewCell) {
            DispatchQueue.global().async {
                let data = try? Data(contentsOf: imageUrl)
                if let data = data {
                    let image = UIImage(data: data)
                    DispatchQueue.main.async {
                        imageCell.imageView.image = image
                    }
                }
            }
    }
    
    private func loadImage(imageUrl: URL, controller: ImageViewController) {
        DispatchQueue.global().async {
            let data = try? Data(contentsOf: imageUrl)
            if let data = data {
                let image = UIImage(data: data)
                DispatchQueue.main.async {
                     controller.imageView.image = image!
                }
               
            }
        }
    }
    
    
}
