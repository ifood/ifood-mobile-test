platform :ios, '10.0'

def shared
end

def objectiveC
end

def rxLibs
    pod 'RxSwift'
    pod 'RxCocoa'
    pod 'RxDataSources'
    pod 'NSObject+Rx'
    pod 'RxKeyboard'
end

def network
  pod 'Moya/RxSwift'
end

def libs
  shared
  objectiveC
  rxLibs
  pod 'SnapKit'
  pod 'Kingfisher'
  pod 'SwiftLint'
  pod 'SwiftGen'
  pod 'KeychainSwift'
end

def lib_tests
  pod 'RxBlocking'
  pod 'RxTest'
end

target 'Domain' do
  use_frameworks!
  libs
  target 'DomainTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'Network' do
  use_frameworks!
  network
  libs
  target 'NetworkTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'Utility' do
  use_frameworks!
  network
  libs
  target 'UtilityTests' do
    inherit! :search_paths
    lib_tests
  end
end

target 'Resources' do
  use_frameworks!
end

target 'TwitterSentiment' do
  use_frameworks!
  libs
  target 'TwitterSentimentTests' do
    inherit! :search_paths
    lib_tests
  end
  target 'TwitterSentimentUITests' do
    inherit! :search_paths
    lib_tests
  end

end
