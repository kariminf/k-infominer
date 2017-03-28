# KInfoMiner

[![Project](https://img.shields.io/badge/Project-KInfoMiner-4B0082.svg)](https://github.com/kariminf/KInfoMiner)
[![Version](https://img.shields.io/badge/Version-1.0.0-4B0082.svg)](https://github.com/kariminf/KInfoMiner/releases)
[![License](https://img.shields.io/badge/License-Apache_2-4B0082.svg)](http://www.apache.org/licenses/LICENSE-2.0)
[![Travis](https://img.shields.io/travis/kariminf/KInfoMiner.svg)](https://travis-ci.org/kariminf/KInfoMiner)
[![codecov](https://img.shields.io/codecov/c/github/kariminf/KInfoMiner.svg)](https://codecov.io/gh/kariminf/KInfoMiner)
[![jitpack](https://jitpack.io/v/kariminf/KInfoMiner.svg)](https://jitpack.io/#kariminf/KInfoMiner)

**Kariminf Infomation Miner**

Was, first, designed as a parser to extract information from [Jim Breen's Edict2 dictionary](http://www.edrdg.org/jmdict/edict_doc.html).  
But, why not create a global project and extend this one with a wider vision:
* Create parsers for all sort of dictionaries and files
* Save the extracted information in a database (current sqlite)


## Use
ou can download the jar from "release" section and link it to your project;
Or you can use https://jitpack.io to manage dependency.
Replace "tag" with the release tag; for example "1.0.0".

### Gradle

Add this to your "build.gradle":
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.kariminf:KInfoMiner:tag'
}
```

### Maven

```xml
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
</repositories>

<dependency>
	    <groupId>com.github.kariminf</groupId>
	    <artifactId>KInfoMiner</artifactId>
	    <version>tag</version>
</dependency>
```

## License

Copyright (C) 2013,2017 Abdelkrime Aries

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

[http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
