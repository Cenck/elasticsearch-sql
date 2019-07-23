# 说明
之前使用Druid的SqlParser组件实现了一个[elasticsearch-sql](https://github.com/iamazy/elasticsearch-sql),实现一般的功能还好，后来发现需要实现一些复杂的搜索（如nested，地理信息搜索）的时候，越来越受到SqlParser的限制，于是决定将`elasticsearch-sql`这个工具使用antlr4进行重写，并添加多语言版本的实现
