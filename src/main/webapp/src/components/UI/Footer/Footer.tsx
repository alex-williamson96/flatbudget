import { useRoute } from "wouter";

const Footer = () => {
  const [isBudget, params] = useRoute("/budget");
  if (!isBudget) {
    return (
      <footer className="bg-gray-900 text-white py-4 fixed clear-both bottom-0 left-0 w-full">
        <div className="container mx-auto px-4">
          <div className="flex flex-wrap justify-between items-center">
            <div className="text-sm">
              <p>&copy; 2023 Flat Budget. All rights reserved.</p>
            </div>
            <div className="text-sm">
              <p>
                Designed by{" "}
                <a href="#" className="text-blue-500 hover:text-blue-700">
                  Alex Williamson
                </a>
              </p>
            </div>
          </div>
        </div>
      </footer>
    );
  }

  return <></>
};

export default Footer;
