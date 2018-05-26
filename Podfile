
platform :ios, '9.0'

target 'Twimotion' do
  use_frameworks!

  # Swift lint/gen via cocoapods to run on CI/CD
  pod 'SwiftLint'
  pod 'SwiftGen'

  # RxSwift
  pod 'RxSwift',    '~> 4.0'
  pod 'RxCocoa',    '~> 4.0'

  target 'TwimotionTests' do
    inherit! :search_paths
    
  end

  target 'TwimotionUITests' do
    inherit! :search_paths
    
  end

  # Remove Code coverage for Pods target
  post_install do |installer_representation|
    installer_representation.pods_project.targets.each do |target|
        target.build_configurations.each do |config|
            config.build_settings['CLANG_ENABLE_CODE_COVERAGE'] = 'NO'
        end
    end
  end

end
