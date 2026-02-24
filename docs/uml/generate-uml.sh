#!/bin/bash

echo "🎨 开始生成UML类图..."

# 检查PlantUML是否安装
if ! command -v plantuml &> /dev/null
then
    echo "❌ PlantUML未安装"
    echo "请先安装PlantUML:"
    echo "  macOS: brew install plantuml"
    echo "  Ubuntu: apt-get install plantuml"
    exit 1
fi

# 进入UML目录
cd "$(dirname "$0")"

# 生成PNG格式
echo "📷 生成PNG格式..."
plantuml -tpng *.puml

# 生成SVG格式
echo "🖼️ 生成SVG格式..."
plantuml -tsvg *.puml

# 统计生成的文件
png_count=$(ls -1 *.png 2>/dev/null | wc -l)
svg_count=$(ls -1 *.svg 2>/dev/null | wc -l)

echo ""
echo "✅ UML类图生成完成！"
echo "📊 统计信息:"
echo "   PNG图片: $png_count 个"
echo "   SVG图片: $svg_count 个"
echo ""
echo "📁 文件位置: docs/uml/"
echo ""
echo "💡 提示: 你可以在Markdown中引用这些图片"

