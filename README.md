# android-image-client

Project provided as a test environment for handling
*  out of memory error while generating bitmaps
* java.util.Base64 and android.util.Base64 inconsistency

Image service implemented on Spring boot with jpa, postgres and swagger.
Provides basic image service consists upload, get and delete endpoints.

Simple one page Android Application used as a client.

Postgres served with docker-compose.

When you are done with postgres, spring data source and request url configurations. Just upload couple pictures with different names on swagger client that served on localhost:8390 and enter image names on android client in order to see on ImageView field.
