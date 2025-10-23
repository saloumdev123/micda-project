// utils/ramli.utils.ts
export function binaryToSymbols(binary: string): string {
  return binary
    .split(' ')
    .map(bit => bit === '1' ? '•' : '○')
    .join(' ');
}

export function symbolsToBinary(symbols: string): string {
  return symbols
    .split(' ')
    .map(char => char === '•' ? '1' : '0')
    .join(' ');
}
