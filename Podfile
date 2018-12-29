platform :ios, '10.0'
use_frameworks!

def rxLibs
  pod 'Moya/RxSwift'
    pod 'RxSwift'
    pod 'RxCocoa'
    pod 'RxDataSources'
    pod 'NSObject+Rx'
    pod 'RxKeyboard'
end

def libs
  rxLibs
  pod 'SnapKit'
  pod 'SwiftLint'
  pod 'SwiftGen'
  pod 'KeychainSwift'
  pod 'AlamofireImage'
end

def lib_tests
  pod 'RxBlocking'
  pod 'RxTest', "~> 4.4.0"
end

target 'TwitterSentiment' do
  libs
   target 'TwitterSentimentTests' do
     inherit! :search_paths
     lib_tests
   end
end

target 'Travis' do
  libs
end

target 'TwitterSentimentUITests' do
  libs
end

target 'Domain' do
  libs
  target 'DomainTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'NetworkPlatform' do
  libs
  target 'NetworkPlatformTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'Utility' do
  libs
  target 'UtilityTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'Resources' do
  target 'ResourcesTests' do
    inherit! :search_paths
    lib_tests
  end
end
