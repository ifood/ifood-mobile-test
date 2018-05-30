
platform :ios, '9.0'

def common_pods
    # Swift lint/gen via cocoapods to run on CI/CD
    pod 'SwiftLint'
    pod 'SwiftGen'
    
    # Security
    pod 'SwiftKeychainWrapper'
    
    # RxSwift
    pod 'RxSwift',    '~> 4.0'
    pod 'RxCocoa',    '~> 4.0'
    pod 'RxDataSources', '~> 3.0'
    
    # ImageLoader
    pod 'PINRemoteImage', '~> 2.1.4'
end

def common_test_pods
    pod 'Nimble'
    pod 'Quick'
    pod 'RxBlocking', '~> 4.0'
    pod 'RxTest',     '~> 4.0'
end

target 'Twimotion' do
  use_frameworks!
  
  common_pods
  
  target 'TwimotionTests' do
    inherit! :search_paths
    common_test_pods
  end

  target 'TwimotionUITests' do
    inherit! :search_paths
    
  end

end


# Remove Code coverage for Pods target
post_install do |installer_representation|
    installer_representation.pods_project.targets.each do |target|
        target.build_configurations.each do |config|
            config.build_settings['CLANG_ENABLE_CODE_COVERAGE'] = 'NO'
        end
    end
end
