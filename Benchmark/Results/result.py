class Result:
        """
                Class for storing the benchmark result
        """
        def __init__(self):
                self.elapsed_time = 0
                self.cpu_cycles = 0
                self.instructions = 0
                self.ipc = 0
                self.branch_misses = 0
                self.branch_instructions = 0
                self.branch_mispredict_rate = 0
                self.name = ''

        def __str__(self):
                result = ''
                result += 'Benchmark name: %s\n' % self.name
                result += 'Elapsed Time: %s\n' % self.elapsed_time
                result += 'Cpu Cycles: %s\n' % self.cpu_cycles
                result += 'Instructions: %s\n' % self.instructions
                result += 'IPC : %s\n' % self.ipc
                result += 'Branch Misses: %s\n' % self.branch_misses
                result += 'Branch Instructions: %s\n' % self.branch_instructions
                result += 'Branch MisPredict Rate: %s\n' % self.branch_mispredict_rate
                return result

