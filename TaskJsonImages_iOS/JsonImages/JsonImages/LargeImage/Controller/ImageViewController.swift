import UIKit

class ImageViewController: UIViewController {

    @IBOutlet weak var imageView: UIImageView!
    
   private var image = UIImage()
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

       imageView.image = image
    }
    
}
