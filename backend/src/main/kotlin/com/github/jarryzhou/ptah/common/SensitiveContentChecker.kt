package com.github.jarryzhou.ptah.common

import org.springframework.beans.factory.InitializingBean

class SensitiveContentChecker : InitializingBean {
    private val nodes: MutableMap<Int, _root_ide_package_.com.github.jarryzhou.ptah.common.WordNode>? = null // 存储敏感词节点
    private val stopWordSet: MutableSet<Int>? = null // 停顿词
    private var sensitiveWordsProvider: _root_ide_package_.com.github.jarryzhou.ptah.common.SensitiveWordsProvider? = null
    private var stopWordsProvider: _root_ide_package_.com.github.jarryzhou.ptah.common.SensitiveWordsProvider? = null
    var isEnabled = true

    private fun addStopWord(words: List<String>?) {
        if (!words.isNullOrEmpty()) {
            val stopWords = words.flatMap { it.toCharArray().map { c -> charConvert(c) } }
            stopWordSet?.addAll(stopWords)
        }
    }

    private fun addSensitiveWord(words: List<String?>?) {
        words?.forEach { word ->
            if (word.isNullOrEmpty()) return@forEach
            val charArray = word.toCharArray()
            val fchar = charConvert(charArray[0])
            var fnode = nodes!![fchar]
            if (fnode == null) {
                fnode = _root_ide_package_.com.github.jarryzhou.ptah.common.WordNode(fchar, charArray.size == 1)
                nodes[fchar] = fnode
            } else {
                if (!fnode.isLast && charArray.size == 1) fnode.isLast = true
            }
            for (i in 1 until charArray.size) {
                fnode = fnode!!.addIfNoExist(charConvert(charArray[i]), i == charArray.lastIndex)
            }
        }
    }

    /**
     * 过滤判断 将敏感词转化为成屏蔽词
     */
    fun checkText(textContent: String?): String? {
        if (!isEnabled) {
            return textContent
        }
        if (nodes.isNullOrEmpty()) {
            return textContent
        }
        if (textContent.isNullOrEmpty()) {
            return textContent
        }
        val replacedTextContent = internalReplacement(textContent)
        // 把文本拆分成字符
        val textChars = replacedTextContent.toCharArray()
        val length = textChars.size
        var currentChar: Int // 当前检查的字符
        var currentCharBackup: Int // 当前检查字符的备份
        var k: Int
        var node: _root_ide_package_.com.github.jarryzhou.ptah.common.WordNode?
        // 遍历字符
        var i = 0
        while (i < length) {
            // 获得当前检查字符
            currentChar = charConvert(textChars[i])
            // 如果敏感首字中不包含当前字符，直接跳过
            if (!nodes.containsKey(currentChar)) {
                i++
                continue
            }
            // 如果命中敏感首字，查询首字对应的敏感词树
            node = nodes[currentChar] // 日 2
            // 如果敏感词数为空，跳过，一般不会有这种情况
            if (node == null) {
                i++
                continue
            }
            var couldMark = false
            var markNum = -1
            // 单字匹配（日）
            if (node.isLast) {
                couldMark = true
                markNum = 0
            }
            // 继续匹配（日你/日你妹），以长的优先
            // 你-3 妹-4 夫-5
            k = i
            currentCharBackup = currentChar // 当前字符的拷贝
            while (++k < length) {
                val temp = charConvert(textChars[k])
                if (temp == currentCharBackup) {
                    continue
                }
                // 如果后面是停顿词，表示句子完了，跳过
                if (stopWordSet != null && stopWordSet.contains(temp)) {
                    continue
                }
                // 在子节点中找字符对应的节点
                node = node!!.querySub(temp)
                if (node == null) {
                    break
                }
                // 如果找到的子节点是敏感词结束，那么就匹配了
                if (node.isLast) {
                    couldMark = true
                    markNum = k - i // 3-2
                }
                currentCharBackup = temp
            }

            // 如果找到了，提取出来
            if (couldMark) {
                k = 0
                while (k <= markNum) {
                    textChars[k + i] = SIGN
                    k++
                }
                i += markNum
            }
            i++
        }
        return String(textChars)
    }

    private fun internalReplacement(src: String?): String {
        return if (src.isNullOrEmpty()) {
            ""
        } else {
            src.replace("傻逼".toRegex(), "笨笨").replace("傻B".toRegex(), "笨笨").replace("傻缺".toRegex(), "笨笨")
                .replace("煞笔".toRegex(), "笨笨").replace("煞逼".toRegex(), "笨笨").replace("傻笔".toRegex(), "笨笨")
                .replace("傻x".toRegex(), "笨笨").replace("傻\\*".toRegex(), "笨笨")
        }

        // 以下为例子
    }

    /**
     * 是否包含敏感词
     */

    operator fun contains(src: String): Boolean {
        if (nodes == null) return false
        val chars = src.toCharArray()

        for (i in chars.indices) {
            val currChar = charConvert(chars[i])
            if (!nodes.containsKey(currChar)) continue
            var couldMark = false
            var node = nodes[currChar]
            couldMark = node?.isLast!!

            var k = i
            var prevChar = currChar
            while (++k < chars.size) {
                val nextChar = charConvert(chars[k])
                if (nextChar == prevChar || (stopWordSet != null && stopWordSet.contains(nextChar))) {
                    continue
                }
                node = node?.querySub(nextChar)
                if (node == null) {
                    break
                }
                if (node.isLast) {
                    couldMark = true
                }
                prevChar = nextChar
            }

            if (couldMark) {
                return true
            }
        }

        return false
    }

    /**
     * 大写转化为小写 全角转化为半角
     */
    private fun charConvert(src: Char): Int {
        var r = src.code
        if (src in SBC_CHAR_START..SBC_CHAR_END) { // 如果位于全角！到全角～区间内
            r = src.code - CONVERT_STEP
        } else if (src == SBC_SPACE) { // 如果是全角空格
            r = DBC_SPACE.code
        }
        return r + if (r >= 'A'.code && r <= 'Z'.code) 32 else 0
    }

    // 初始化后开始加载词库到redis
    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        addSensitiveWord(sensitiveWordsProvider!!.provide())
        addStopWord(stopWordsProvider!!.provide())
    }

    companion object {
        /**
         * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
         */
        const val SBC_CHAR_START = 65281 // 全角！
            .toChar()

        /**
         * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
         */
        const val SBC_CHAR_END = 65374 // 全角～
            .toChar()

        /**
         * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
         */
        const val CONVERT_STEP = 65248 // 全角半角转换间隔

        /**
         * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
         */
        const val SBC_SPACE = 12288 // 全角空格 12288
            .toChar()

        /**
         * 半角空格的值，在ASCII中为32(Decimal)
         */
        const val DBC_SPACE = ' ' // 半角空格
        private const val SIGN = '*' // 敏感词过滤替换
    }
}
