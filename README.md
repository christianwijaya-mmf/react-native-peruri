
# react-native-peruri

## Getting started

`$ npm install react-native-peruri --save`

### Mostly automatic installation

`$ react-native link react-native-peruri`

### Manual installation


#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import co.id.peruri.RNPeruriPackage;` to the imports at the top of the file
  - Add `new RNPeruriPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-peruri'
  	project(':react-native-peruri').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-peruri/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-peruri')
  	```


## Usage
```javascript
import RNPeruri from 'react-native-peruri';

RNPeruri.startVideo()
	.then(() => {
		// todo
	})
	.catch(() => {
		// todo
	})

```
  # react-native-peruri
