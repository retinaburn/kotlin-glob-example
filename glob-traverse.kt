import java.io.File
import java.nio.file.PathMatcher
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Paths

/**
*
* Take a list of files and/or globs on the command line and perform some action
*
**/
fun main(args: Array<String>){
  if (args.isEmpty()){
    println(usage())
    return
  }
  for (arg in args){
    val file = File(arg)

    if (file.exists()){
      handleFile(file)
    } else {
      val ds = Files.newDirectoryStream(file.getParentFile().toPath(), file.getName())

      for(path in ds){
        handleFile(path.toFile())
      }
    }
  }
}

/*
* Do something interesting with the file
*/
fun handleFile(f: File){
  println(f)
}

fun usage(): String{
  return """
  Usage: java -jar glob-traverse.jar [file(s)] [glob(s)]
    file(s) is a file or list of file(s)
    glob(s) is a glob or list of glob(s)

      Example:
       java -jar glob-traverse.jar sampleFile.txt")
       java -jar glob-traverse.jar sampleFile.txt sample2.zip sample3.zip")
       java -jar glob-traverse.jar sampleFile.txt ../*.zip")
       java -jar glob-traverse.jar /path/somewhere/fun/2017-*")
""".trimIndent()
}
