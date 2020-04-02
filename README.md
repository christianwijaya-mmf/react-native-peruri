
# react-native-react-native-peruri

## Getting started

`$ npm install react-native-react-native-peruri --save`

### Mostly automatic installation

`$ react-native link react-native-react-native-peruri`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import co.id.peruri.RNReactNativePeruriPackage;` to the imports at the top of the file
  - Add `new RNReactNativePeruriPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-react-native-peruri'
  	project(':react-native-react-native-peruri').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-react-native-peruri/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-react-native-peruri')
  	```


## Usage
```javascript
import RNReactNativePeruri from 'react-native-react-native-peruri';

// TODO: What to do with the module?
RNReactNativePeruri;
```
  # react-native-peruri
